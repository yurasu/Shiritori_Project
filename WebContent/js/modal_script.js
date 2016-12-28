$(function() {
	if ($("#modal-overlay")[0])
		return false;
	$("body").append('<div id="modal-overlay"></div>');
	$("#modal-overlay").fadeIn("slow");
	$("#modal").fadeIn("slow");
});