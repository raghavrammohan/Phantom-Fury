package com.phantomfury.leetcode.junechallenge;

// Day 16
public class ValidateIPAddresses
{
    public boolean isValidIPv6(String s)
    {
        String str = "0123456789abcdefABCDEF";
        if(s.endsWith(":"))
            return false;
        String[] parts = s.split(":");
        System.out.println(parts.length);
        if(parts.length == 8)
        {
            for(int i = 0; i < 8; i++)
            {
                if(parts[i] == null || parts[i].equals("") || parts[i].length() > 4)
                    return false;
                for(int j = 0; j < parts[i].length(); j++)
                    if(str.indexOf(parts[i].charAt(j)) == -1)
                        return false;
            }
            return true;
        }
        return false;
    }

    public boolean isValidIPv4(String s)
    {
        String str = "0123456789";
        if(s.endsWith("."))
            return false;
        String[] parts = s.split("\\.");
        if(parts.length == 4)
        {
            for(int i = 0; i < 4; i++)
            {
                if(parts[i] == null || parts[i].equals("") || parts[i].length() > 3 || (parts[i].charAt(0) == '0' && parts[i].length() > 1))
                    return false;
                for(int j = 0; j < parts[i].length(); j++)
                    if(str.indexOf(parts[i].charAt(j)) == -1)
                        return false;
                int num = Integer.parseInt(parts[i]);
                if(num < 0 || num > 255)
                    return false;
            }
            return true;
        }
        return false;
    }

    public String validIPAddress(String IP) {

        if(IP.indexOf(":") != -1 && isValidIPv6(IP))
            return "IPv6";
        else if(IP.indexOf(".") != -1 && isValidIPv4(IP))
            return "IPv4";

        return "Neither";
    }

    public static void main(String[] args) {
        String ip1 = "172.192.8.1";
        String ip2 = "256.255.255.255";
        String ip3 = "172.192.8.1.";
        String ip4 = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String ip5 = "2001:db8:85a3:0:0:8A2E:0370:7334";
        String ip6 = "2001:db8:85a3:0:0:8A2E:0370:7334:";
        String ip7 = "2001:0db8:85a3::8A2E:0370:7334";

        ValidateIPAddresses obj = new ValidateIPAddresses();
        System.out.println(obj.validIPAddress(ip1));
        System.out.println(obj.validIPAddress(ip2));
        System.out.println(obj.validIPAddress(ip3));
        System.out.println(obj.validIPAddress(ip4));
        System.out.println(obj.validIPAddress(ip5));
        System.out.println(obj.validIPAddress(ip6));
        System.out.println(obj.validIPAddress(ip7));
    }
}
