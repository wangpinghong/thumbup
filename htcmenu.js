// 避免無法正確取得選單圖片的寬高
// 因此動作延遲到 window.onload
$(window).load(function(){
	// 先取得選單的連結及找出 .selected 的選項
	// 再取得用來當遮罩的 #menuMask
	// 因為遮罩圖片比原來選單圖片大，所以設定要顯示的寬高及位置差距
	var _menu = $('#menu li>a'),
		_target = $(_menu).filter('.selected'),
		_menuMask = $('#menuMask'),
		_maskHeight = 10,
		_diffHeight = 8,
		_maskWidth = 5,
		_diffWidth = 5;
		
	// 如果沒有 .selected 的選項就預設第一個
	if(_target.length<=0){
		_target = $(_menu).eq(0);
	}

	// 圖片預載
	_menu.each(function(){
		$('<img />').attr('src', $(this).attr('id')+'_black.png').hide().appendTo('body');
	});
		
	// 當選單被點擊時..
	_menu.click(function(){
		// 先移除上一個有 .selected 的超連結
		$('.selected').removeClass('selected');

		// 把點擊到的項目加上 .selected
		var _this = $(this).addClass('selected'),
			_link = _this.attr('href');

		// 更換選單的背景圖片及移動選單到該選項上
		// 如果選項有連結時，則會在動畫移動完後來轉址
		_menuMask.css({
			backgroundImage: 'url('+_this.attr('id')+'_black.png)'
		}).stop().animate({
			width: $(this).width() + _maskWidth,
			height: $(this).height() + _maskHeight,
			top: $(this).offset().top - _diffHeight,
			left: $(this).offset().left - _diffWidth
		}, function(){
			// 如果有網址
			if(!!_link){
				// 如果有 target
				var _targetAttr = _this.attr('target');
				if(!!_targetAttr){
					// target = '_blank' 則開新窗
					if(_targetAttr.toLowerCase()=='_blank'){
						open(_link);
					}else{
						// 連結開在指定的 target id 上
						$('#'+_targetAttr).attr('src', _link);
					}
				}else{
					location = _link;
				}
			}
		});

		return false;
	}).focus(function(){
		$(this).blur();	
	});
		
	// 當瀏覽器尺寸改變時也要重新移動位置
	$(window).resize(function () {
		moveMenu($(_menu).filter('.selected'));
	});
		
	// 移動遮罩到指定的選項上面
	function moveMenu(obj){
		_menuMask.css({
			width: obj.width() + _maskWidth,
			height: obj.height() + _maskHeight,
			top: obj.offset().top - _diffHeight,
			left: obj.offset().left - _diffWidth,
			backgroundImage: 'url('+obj.attr('id')+'_black.png)'
		});
	}

	// 網頁載入後先移動遮罩
	moveMenu(_target);
});