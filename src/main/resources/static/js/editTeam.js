function replaceMembers(html) {
    // Replace the <fieldset id="members"> with a new one returned by server.
    $('#members').replaceWith($(html));
}

$('button[id="addMemberBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter "addMember" to POSTed form data. Button's name and value is
    // POSTed only when clicked. Since "event.preventDefault();" prevents from
    // actual clicking the button, following line will add parameter to form
    // data.
    data += '&addMember=' + $('#addMember').val();
    $.post(window.location.href, data, replaceMembers);
    $("#addMemberModal").modal('toggle')
});

$('button[id="removeMemberBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter and index of item that is going to be removed.
    data += '&removeMember=' + $(this).val();
    $.post(window.location.href, data, replaceMembers);
});

function replaceApprovers(html) {
    // Replace the <fieldset id="Approvers"> with a new one returned by server.
    $('#approvers').replaceWith($(html));
}

$('button[id="addApproverBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter "addApprover" to POSTed form data. Button's name and value is
    // POSTed only when clicked. Since "event.preventDefault();" prevents from
    // actual clicking the button, following line will add parameter to form
    // data.
    data += '&addApprover=' + $('#addApprover').val();
    $.post(window.location.href, data, replaceApprovers);
    $("#addApproverModal").modal('toggle')
});

$('button[id="removeApproverBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter and index of item that is going to be removed.
    data += '&removeApprover=' + $(this).val();
    $.post(window.location.href, data, replaceApprovers);
});


$('button[id="saveBtn"]').click(function (event) {
    event.preventDefault();
    var data = $('form').serialize();
    // Add parameter and index of item that is going to be removed.
    $("#teamForm").submit();
});