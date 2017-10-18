<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<link href="/static/third/trumbowyg/design/css/trumbowyg.css" rel="stylesheet">
		
		<script src="/static/third/trumbowyg/trumbowyg.js"></script>
        <script src="/static/third/trumbowyg/langs/fr.js"></script>
        <script src="/static/third/trumbowyg/plugins/upload/trumbowyg.upload.js"></script>
        <script src="/static/third/trumbowyg/plugins/base64/trumbowyg.base64.js"></script>
        <script>
            /** Default editor configuration **/
            $('#simple-editor').trumbowyg();

            $.trumbowyg.btnsGrps.test = ['bold', 'link'];

            /* Add new words for customs btnsDef just below */
            $.extend(true, $.trumbowyg.langs, {
                fr: {
                    align: 'Alignement',
                    image: 'Image'
                }
            });
            $('#customized-buttonpane').trumbowyg({
                lang: 'fr',
                closable: true,
                fixedBtnPane: true,
                btnsDef: {
                    // Customizables dropdowns
                    align: {
                        dropdown: ['justifyLeft', 'justifyCenter', 'justifyRight', 'justifyFull'],
                        ico: 'justifyLeft'
                    },
                    image: {
                        dropdown: ['insertImage', 'upload', 'base64'],
                        ico: 'insertImage'
                    }
                },
                btns: ['viewHTML',
                    '|', 'formatting',
                    '|', 'btnGrp-test',
                    '|', 'align',
                    '|', 'btnGrp-lists',
                    '|', 'image']
            });
            
            $('.editor').on('dblclick', function(e){
                $(this).trumbowyg({
                    lang: 'fr',
                    closable: true,
                    fixedBtnPane: true
                });
            });
        </script>