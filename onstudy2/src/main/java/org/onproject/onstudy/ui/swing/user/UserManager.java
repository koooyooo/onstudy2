/******************************************************************************
  Source  : $Source: /CVSNT/repository/OnStudy2/src/java/org/onproject/onstudy/ui/swing/user/UserManager.java,v $
  Version : $Revision: 1.5 $
  Date    : $Date: 2007/06/22 08:37:17 $
******************************************************************************/
package org.onproject.onstudy.ui.swing.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URL;

import org.apache.commons.io.IOUtils;

/**
 * ���[�U���Ǘ�����}�l�[�W��
 * 
 * @author ���c �D�f
 */
public class UserManager {
    
    /** ���[�U�� */
    private String userName;
    
    /**
     * ���[�U����ݒ肵�܂��B
     * 
     * @param userName ���[�U��
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * ���[�U�����擾���܂��B
     * 
     * @return ���[�U��
     */
    public String getUserName() {
        return this.userName;
    }
    
    /**
     * ���[�U�����t�@�C�����擾���܂��B
     * 
     * @throws Exception
     */
    public void load() throws Exception {
        BufferedReader br = null;
        InputStream in = null;
        try {
            in = this.getClass().getResourceAsStream("/username.txt");
            br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            for (String line; (line = br.readLine()) != null; ) {
                final String trimmedLine = line.trim();
                if (trimmedLine.startsWith("#")) {
                    continue;
                }
                if ("".equals(trimmedLine)) {
                    continue;
                }
                this.userName = trimmedLine;
            }
            if (this.userName == null) {
                this.userName = InetAddress.getLocalHost().getHostName();
            }
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(br);
        }
    }
    
    /**
     * ���[�U�����t�@�C���ɕۑ����܂��B
     * 
     * @throws Exception
     */
    public void save() throws Exception {
        PrintWriter pw = null;
        try {
            URL url = this.getClass().getResource("/username.txt");
            pw = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(url.getFile()))));
            pw.print(this.userName);
        } finally {
            pw.close();
        }
    }
    
}
