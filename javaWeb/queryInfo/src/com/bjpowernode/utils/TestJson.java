package com.bjpowernode.utils;

import com.bjpowernode.entity.Province;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJson {
    public static void main(String[] args) throws JsonProcessingException {
        //ʹ��Jackson,��java����תΪjson��ʽ���ַ���

        Province p = new Province(1,"�ӱ�","��","ʯ��ׯ");

        //ʹ��jackson �� p תΪ json
        ObjectMapper om = new ObjectMapper();
        //writeValueAsString���Ѳ�����java����תΪjson��ʽ���ַ���
        String json = om.writeValueAsString(p);
        System.out.println("ת����json = " + json);
    }
}
