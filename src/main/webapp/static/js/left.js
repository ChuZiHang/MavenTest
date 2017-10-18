/**
 * Created by 颂 on 2016/6/30.
 */
$(function() {

	// 左侧导航划过图标变化
	$('.navtree li').on('click', function(event) {
		if ($(this).find('i').attr('data-tag') === undefined) {

			var url = $(this).attr('data-url');
			if (url != undefined && url.length > 0) {
				location.href = url;
				return;
			}
			$(this).find('.childNav').slideToggle('100', function() {
				if ($(this).is(':hidden')) {
					$(this).parent().find('em').removeClass('on');
				} else {
					$(this).parent().find('em').addClass('on');
				}
			});
		}
	});

});