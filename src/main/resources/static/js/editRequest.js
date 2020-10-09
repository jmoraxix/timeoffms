window.onload = function() {
//  $('#startDate').datepicker({
//      daysOfWeekDisabled: "0",
//      daysOfWeekHighlighted: "1,2,3,4,5,6"
//  });
//  $('#endDate').datepicker({
//      daysOfWeekDisabled: "0",
//      daysOfWeekHighlighted: "1,2,3,4,5,6"
//  });
//  $.fn.datepicker.defaults.format = "mm/dd/yyyy";
//  $('#startDate').datepicker();
//  $('#endDate').datepicker();
};
$('button[id="saveBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter and index of item that is going to be removed.
//    $("#requestForm").submit();
    data += '&save';
    $.post(window.location.href, data);
});