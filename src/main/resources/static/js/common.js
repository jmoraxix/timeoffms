function removeRow(btn) {
  let currRow = btn.closest("tr");
  currRow.parentNode.removeChild(currRow);
}