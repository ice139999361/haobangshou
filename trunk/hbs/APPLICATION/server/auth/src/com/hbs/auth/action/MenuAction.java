/**
 * system ��hbs
 * desc:    
 * version: 1.0
 * author : yangzj
 */
package com.hbs.auth.action;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hbs.common.action.base.BaseAction;
import com.hbs.domain.auth.pojo.Menu;
import com.hbs.domain.auth.pojo.Resource;


public class MenuAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * logger.
	 */
	private static final Logger logger = Logger.getLogger(MenuAction.class);
	public String doMenu2() {
		logger.debug("��ʼ��֯�˵����ݣ�");
		try {
			// ������Դ��Ϣ
			com.hbs.common.authfilter.User user = null;
			List<Menu> menuList = null;
			
			user = (com.hbs.common.authfilter.User)getSession().getAttribute("user");
			if(user == null){
				return ERROR;
			}
			logger.debug("��ȡ������û��˵���Դ��Ϣ��");
			List<Resource> resList  = user.getResList();
			if(null != resList && resList.size() >0){
				menuList = new ArrayList<Menu>();
				for(Resource res : resList){
					if(res.getParent() == 0){
						Menu menu = new Menu();
						menu.setId(res.getResourceId());
						menu.setLeaf(false);
						menu.setMenuOrder(res.getResourceId().toString());
						menu.setText(res.getResourceName());
						menu.setUrl(res.getUrlAddress());
						menuList.add(menu);
					}
				}
				
				if(menuList.size() >0){
					for(Menu mm : menuList){
						int mId = mm.getId();
						for(Resource res : resList){
							int iResPar = res.getParent();
							if(mId == iResPar){
								Menu menu = new Menu();
								menu.setId(res.getResourceId());
								menu.setLeaf(true);
								menu.setMenuOrder(res.getResourceId().toString());
								menu.setText(res.getResourceName());	
								menu.setUrl((res.getUrlAddress()));
								mm.addChildren(menu);
							}
						}
					}
					
					//����˵���
					int iSize = menuList.size();
					StringBuilder sb = new StringBuilder();
					sb.append("{");
					sb.append(" \"domain\": \"js\",");
					sb.append(" \"subsystem\": \"js_admin\",");
					sb.append("\"data\": [");
					for(int i =0 ; i< iSize;i++){
						Menu menu = menuList.get(i);
						sb.append(menu.toString());
						if(i != iSize -1){
							sb.append(",");
						}
					}
					sb.append("]");
				//	setResult("data",sb.toString());
					sb.append("}");
					logger.debug("this menu is " + sb.toString());
					HttpServletResponse response = this.getHttpServletResponse();
					 response.setContentType("text/html;charset=utf-8");
				     response.setHeader("Cache-Control", "no-cache");
				     PrintWriter pw=new PrintWriter(new OutputStreamWriter(response.getOutputStream(),"utf-8"));
			         pw.write(sb.toString());
			         pw.flush();
			         pw.close();
				}
			}else{
				logger.error("���û��޿��ò˵���");
				setErrorReason("���û��޿��ò˵���");
				return ERROR;
			}
			return SUCCESS;
		} catch(Exception e) {
			logger.error("catch Exception in doMenu2", e);
			setErrorReason("�ڲ�����");
			return ERROR;
		}
		
		
	}
}
