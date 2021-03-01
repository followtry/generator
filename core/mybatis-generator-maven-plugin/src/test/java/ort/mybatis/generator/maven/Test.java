/*
 *    Copyright 2006-2021 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package ort.mybatis.generator.maven;

import com.taobao.tddl.client.jdbc.TDataSource;
import com.taobao.tddl.common.jdbc.IConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author followtry
 * @since 2021/3/1 10:53 上午
 */
public class Test {

    /**
     * main.
     */
    public static void main(String[] args) throws Exception {
        TDataSource ds = new TDataSource();
        ds.setAppName("ORDER_DATA_CENTER_APP");
        ds.setDynamicRule(true);
        ds.init();
        //ort.mybatis.generator.maven.test测试输出
        System.out.println("init done");
        Connection conn = ds.getConnection();

        PreparedStatement ps = conn.prepareStatement("select * from sequence limit 10");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            StringBuilder sb = new StringBuilder();
            int count = rs.getMetaData().getColumnCount();
            for (int i = 1; i <= count; i++) {
                String key = rs.getMetaData().getColumnLabel(i);
                Object val = rs.getObject(i);
                sb.append("[" + rs.getMetaData().getTableName(i) + "." + key + "->" + val + "]\n");
            }
            System.out.println(sb.toString());
            System.out.println();
        }
        rs.close();
        ps.close();
        conn.close();
        System.out.println("query done");
    }
}
