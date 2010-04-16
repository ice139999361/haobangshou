function printsetup() { 
	// 打印页面设置 
	wb.execwb(8,1); 
} 

function printpreview() {
	var printdiv = document.getElementById("printdiv");
	printdiv.className = "noprint";
	// 打印页面预览 
	wb.execwb(7,1); 
	printdiv.className = "";
} 

function printit() { 
	var printdiv = document.getElementById("printdiv");
	printdiv.className = "noprint";
	if (confirm('确定打印吗？')) { 
		wb.execwb(6,6) 
	}
	printdiv.className = "";
}