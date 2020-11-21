package yanyv.mms.web;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.json.JSONObject;

import yanyv.mms.code.Query;
import yanyv.mms.code.Signup;
import yanyv.mms.vo.Account;

public class SignupWeb {
	public static boolean Signup(int uid, String mid) throws Exception {
		String queryUserByIdUrl = "http://" + IPConfig.IP + "/signupback";

		URL url = new URL(queryUserByIdUrl);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "8859_1");
		out.write("uid=" + uid);
		out.write("&mid=" + mid);
		out.write("&from=" + "ClientPC");
		out.flush();
		out.close();

		// һ�����ͳɹ��������·����Ϳ��Եõ��������Ļ�Ӧ��
		String sCurrentLine;
		String sTotalString;
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		l_urlStream = conn.getInputStream();
		// �����װ
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			sTotalString += sCurrentLine + "\r\n";
		}
		// System.out.println(sTotalString);
		JSONObject result = new JSONObject(sTotalString);

		if (result.getInt("code") == Signup.SUCCESS) {
			return true;
		} else {
			JOptionPane.showMessageDialog(null, result.getString("data"), "����", JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
}
