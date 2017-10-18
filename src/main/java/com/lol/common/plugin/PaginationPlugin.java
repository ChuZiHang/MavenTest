package com.lol.common.plugin;

import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

/**
 * 分页插件
 * 
 * @author cunxing
 *
 */
public class PaginationPlugin extends PluginAdapter {

	private static String PageBean = PaginationPlugin.class.getPackage().getName() + ".Page";

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {
		// add field, getter, setter for limit clause
		addPage(topLevelClass, introspectedTable, "page");
		addDialect(topLevelClass, introspectedTable, "dialect");
		return super.modelExampleClassGenerated(topLevelClass,
				introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document,
			IntrospectedTable introspectedTable) {
		XmlElement parentElement = document.getRootElement();

		// 产生分页语句前半部分
		XmlElement paginationPrefixElement = new XmlElement("sql");
		paginationPrefixElement.addAttribute(new Attribute("id",
				"pagePrefix"));
		XmlElement pageStart = new XmlElement("if");
		pageStart.addAttribute(new Attribute("test", "page != null"));
		XmlElement pageDialect1 = new XmlElement("if");
		pageDialect1.addAttribute(new Attribute("test", "dialect == 'oracle'"));
		pageStart.addElement(pageDialect1);
		pageDialect1.addElement(new TextElement(
				"select * from ( select row_.*, rownum rownum_ from ( "));
		paginationPrefixElement.addElement(pageStart);
		parentElement.addElement(paginationPrefixElement);

		// 产生分页语句后半部分
		XmlElement paginationSuffixElement = new XmlElement("sql");
		paginationSuffixElement.addAttribute(new Attribute("id",
				"pageSuffix"));
		XmlElement pageEnd = new XmlElement("if");
		pageEnd.addAttribute(new Attribute("test", "page != null"));
		XmlElement pageDialect2 = new XmlElement("if");
		pageDialect2.addAttribute(new Attribute("test", "dialect == 'oracle'"));
		pageEnd.addElement(pageDialect2);
		pageDialect2.addElement(new TextElement(
				"<![CDATA[ ) row_ ) where rownum_ > #{page.start} and rownum_ <= (#{page.start} + #{page.limit}) ]]>"));

		// ----- mysql语句。
		XmlElement mysqlDialect = new XmlElement("if");
		mysqlDialect.addAttribute(new Attribute("test", "dialect == 'mysql'"));
		pageEnd.addElement(mysqlDialect);
		mysqlDialect.addElement(new TextElement(
				"limit #{page.start} , #{page.limit}"));
		paginationSuffixElement.addElement(pageEnd);

		parentElement.addElement(paginationSuffixElement);

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		element = doAddPageRefrenceXml(element);

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		element = doAddPageRefrenceXml(element);

		return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	/**
	 * 增加分页SQL引用
	 * 
	 * @param element
	 * @return
	 */
	private XmlElement doAddPageRefrenceXml(XmlElement element) {
		XmlElement xmlStart = new XmlElement("include");   
		xmlStart.addAttribute(new Attribute("refid", "pagePrefix"));
		element.getElements().add(0, xmlStart);

		XmlElement xmlEnd = new XmlElement("include");   
		xmlEnd.addAttribute(new Attribute("refid", "pageSuffix"));
		element.getElements().add(xmlEnd);
		return element;
	}

	/**
	 * 添加方言参数
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	private void addDialect(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PRIVATE);
		field.setType(new FullyQualifiedJavaType("String"));
		field.setName(name + " = \"mysql\"");
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				"String"), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType(
				"String"));
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	/**
	 * 添加Page对象参数
	 * 
	 * @param topLevelClass
	 * @param introspectedTable
	 * @param name
	 */
	private void addPage(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable, String name) {
		topLevelClass.addImportedType(new FullyQualifiedJavaType(
				PageBean));
		CommentGenerator commentGenerator = context.getCommentGenerator();
		Field field = new Field();
		field.setVisibility(JavaVisibility.PROTECTED);
		field.setType(new FullyQualifiedJavaType(PageBean));
		field.setName(name);
		commentGenerator.addFieldComment(field, introspectedTable);
		topLevelClass.addField(field);
		char c = name.charAt(0);
		String camel = Character.toUpperCase(c) + name.substring(1);
		Method method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setName("set" + camel);
		method.addParameter(new Parameter(new FullyQualifiedJavaType(
				PageBean), name));
		method.addBodyLine("this." + name + "=" + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
		method = new Method();
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setReturnType(new FullyQualifiedJavaType(
				PageBean));
		method.setName("get" + camel);
		method.addBodyLine("return " + name + ";");
		commentGenerator.addGeneralMethodComment(method, introspectedTable);
		topLevelClass.addMethod(method);
	}

	/**
	 * This plugin is always valid - no properties are required
	 */
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(PageBean);
	}
}
