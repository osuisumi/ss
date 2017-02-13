function downloadExcel(fileName){
	$('#downloadExcelForm input[name="fileName"]').val(fileName);
	$('#downloadExcelForm').submit();
}

function removeDeleteCheckBox(formId){
	$('#'+formId+' input[type="checkbox"]:checked').remove();
}