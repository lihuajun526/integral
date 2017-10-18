package com.operational.platform.taskbreak;

import com.operational.platform.taskbreak.bean.SpringContext;
import com.operational.platform.taskbreak.service.MqService;

import java.util.HashMap;

/**
 * Created by lihuajun on 2017/10/12.
 */
public class Producer {

    public static void main(String[] args) {

        SpringContext.init("classpath:spring/spring.xml");

        MqService mqService = (MqService) SpringContext.getContext().getBean("mqService");

        //String[] areas = "237-北京市#238-天津市#543-河北省#282-山西省#616-内蒙古#413-辽宁省#181-吉林省#624-黑龙江省#239-上海市#542-江苏省#73-浙江省#276-安徽省#382-福建省#532-江西省#508-山东省#638-河南省#115-湖北省#37-湖南省#488-广东省#66-广西省#492-海南省#240-重庆市#503-四川省#497-贵州省#723-云南省#166-西藏#304-陕西省#25-甘肃省#139-青海省#364-宁夏#123-新疆#241-台湾#234-香港#235-澳门#0-全部".split("#");
        String[] areas = "238-天津市".split("#");
        //String[] investorTypes = "1050-天使投资人#1052-机构投资人#2499-机构非投资人员#1053-分析师#1051-企业家/创业者#1049-律师#1054-会计师#1055-媒体记者#0-全部".split("#");
        String[] investorTypes = "0-全部".split("#");
        //String[] industrys = "1-移动互联网#112-汽车交通#13-医疗健康#27-文化娱乐#101-O2O#28-大数据#109-企业服务#111-云服务#31-教育#107-传媒#3-本地生活#14-旅游服务#5-社交网络#6-体育#15-游戏#8-P2P服务#17-可穿戴设备#23-工具软件#24-互联网金融#30-房产服务#39-金融#40-IT服务#12-装修装潢#102-B2C#103-食品饮料#104-环保#105-先进制造#106-传统硬件#108-新材料#110-电子设备#116-企业信息化#117-新能源#118-物联网#119-B2B#121-智能硬件#124-物流配送#125-影视动漫#128-母婴#129-C2C#130-招聘求职#148-家政服务#149-现代农业#172-生鲜#166-政府引导基金#176-VR/AR#181-人工智能#185-网络社区#186-机械制造#188-生物医药#16-广告营销#206-新三板#19-3D打印#0-全部".split("#");
        String[] industrys = "0-全部".split("#");
        for (String area : areas) {
            for (String investorType : investorTypes) {
                for (String industry : industrys) {
                    HashMap<String, String> attr = new HashMap<>();
                    attr.put("investorType", investorType);
                    attr.put("area", area);
                    attr.put("industry", industry);

                    mqService.saveToMq(attr, "break");
                }
            }
        }
    }

}
