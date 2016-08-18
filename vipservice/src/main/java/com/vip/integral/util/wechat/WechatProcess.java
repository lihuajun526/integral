package com.vip.integral.util.wechat;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * ΢��xml��Ϣ���������߼���
 * @author pamchen-1
 *
 */
public class WechatProcess {
	/**
	 * ��������xml����ȡ���ܻظ������ͨ��ͼ�������api�ӿڣ�
	 * @param xml ���յ���΢������
	 * @return	���յĽ��������xml��ʽ���ݣ�
	 */
	public WechatMsg processWechatMag(String xml){

		WechatMsg wechatMsg = null;

		/** ����xml���� */
		Document doc = Jsoup.parse(xml);

		/**
		 * <xml>
		 <ToUserName><![CDATA[toUser]]></ToUserName>
		 <FromUserName><![CDATA[FromUser]]></FromUserName>
		 <CreateTime>123456789</CreateTime>
		 <MsgType><![CDATA[event]]></MsgType>
		 <Event><![CDATA[subscribe]]></Event>
		 </xml>
		 */

		ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);

		/** ���ı���ϢΪ��������ͼ�������api�ӿڣ���ȡ�ظ����� */
		/*String result = "";
		if("text".endsWith(xmlEntity.getMsgType())){
			result = new TulingApiProcess().getTulingResult(xmlEntity.getContent());
		}

		*//** ��ʱ������û�������ǡ���á����ھ�������Ĺ���֮��resultΪ����Ҳ�á����Ƶ�����
		 *  ��Ϊ���ջظ���΢�ŵ�Ҳ��xml��ʽ�����ݣ�������Ҫ�����װΪ�ı����ͷ�����Ϣ
		 * *//*
		result = new FormatXmlProcess().formatXmlAnswer(xmlEntity.getFromUserName(), xmlEntity.getToUserName(), result);

		return result;*/
		return null;
	}
}
