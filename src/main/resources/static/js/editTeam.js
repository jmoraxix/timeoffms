function replaceMembers(html) {
    // Replace the <fieldset id="members"> with a new one returned by server.
    $('#members').replaceWith($(html));
}

function replaceApprovers(html) {
  // Replace the <fieldset id="Approvers"> with a new one returned by server.
  $('#approvers').replaceWith($(html));
}

$('button[id="addMemberBtn"]').click(function (event) {
  updateUsersTable('addMember', replaceMembers)
});


$('button[id="addApproverBtn"]').click(function (event) {
  updateUsersTable('addApprover', replaceApprovers)
});

function updateUsersTable(addType, handleReturn){
  var data = $('form').serialize();
  data += '&' + addType + '=' + $('#' + addType + '').val();
  $.post(window.location.href, data, handleReturn);
}