/*

Holder - 2.2 - client side image placeholders
(c) 2012-2013 Ivan Malopinsky / http://imsky.co

Provided under the MIT License.
Commercial use requires attribution.

*/

$(document).on('click', '.panel-heading span.clickable', function (e) {
	var $this = $(this);
	if(!$this.hasClass('panel-collapsed')){
		$this.parents('.panel').find('.panel-body').slideUp();
		$this.addClass('panel-collapsed');
		$this.find('i').removeClass('fa-chevron-down').addClass('fa-chevron-up');
	}else{
		$this.parents('.panel').find('.panel-body').slideDown();
		$this.removeClass('panel-collapsed');
		$this.find('i').removeClass('fa-chevron-up').addClass('fa-chevron-down');
	}
})