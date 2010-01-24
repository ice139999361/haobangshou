/**************************************
 *
 * 作者：Ice.
 * 日期：2010.01.01
 *
 **************************************/

if(typeof Node == "undefined") {
	Node = {
		 ELEMENT_NODE 															: 1								// 元素节点，表示起始标签与结束标签之间的内容
		,ATTRIBUTE_NODE 														: 2								// 属性节点，由属性名及值组成。此节点类型不能包含子节点
		,TEXT_NODE 																	: 3								// 文本节点
		,CDATA_SECTION_NODE 												: 4								// <![cdata[]]>的对象表现形式，只包含文本节点
		,ENTITY_REFERENCE_NODE 											: 5								// 代表一个实体引用，例如：&quot
		,ENTITY_NODE 																: 6								// 表示 DTD 中一个实体定义，例如：<!entity FOO "FOO">
		,PROCESSING_INSTRUCTION_NODE 								: 7								// 代表一个PI
		,COMMENT_NODE 															: 8								// 代表 XML 注释
		,DOCUMENT_NODE 															: 9								// 最顶层的节点，所有其它节点都属于它
		,DOCUMENT_TYPE_NODE 												: 10							// DTD 引用。例如：<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
		,DOCUMENT_FRAGMENT_NODE 										: 11							// 可以像 Document 一样保存其它节点
		,NOTATION_NODE 															: 12							// 代表在 DTD 中定义的一个记号
		,DOCUMENT_POSITION_DISCONNECTED 						: 1
		,DOCUMENT_POSITION_PRECEDING 								: 2
		,DOCUMENT_POSITION_FOLLOWING 								: 4
		,DOCUMENT_POSITION_CONTAINS 								: 8
		,DOCUMENT_POSITION_CONTAINED_BY 						: 16
		,DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC 	: 32
	}
}