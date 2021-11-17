package com.zg.devicemanager.utils;

import java.io.*;

public class FileUtil {

    //判断路径是否存在，不存在则创建。如：D:\news\2014\12
    public static boolean isexitsPath1(String path)throws InterruptedException {
        String[] paths = path.split("\\\\");
        StringBuffer fullPath = new StringBuffer();
        for (int i = 0; i < paths.length; i++) {
            fullPath.append(paths[i]).append("\\\\");
            File file = new File(fullPath.toString());
            if (!file.exists()) {
                file.mkdir();
                System.out.println("创建目录为：" + fullPath.toString());
                Thread.sleep(1500);
            }
        }
        File file = new File(fullPath.toString());//目录全路径
        if (!file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    //判断路径是否存在，不存在则创建。如：D:\news\2014\12\11.txt
    public boolean isexitsPath2(String path)throws InterruptedException {
        String[] paths = path.split("\\\\");
        StringBuffer fullPath = new StringBuffer();
        for (int i = 0; i < paths.length; i++) {
            fullPath.append(paths[i]).append("\\\\");
            File file = new File(fullPath.toString());
            if (paths.length - 1 != i) {//判断path到文件名时，无须继续创建文件夹！
                if (!file.exists()) {
                    file.mkdir();
                    System.out.println("创建目录为：" + fullPath.toString());
                    Thread.sleep(1500);
                }
            }
        }
        File file = new File(fullPath.toString());//目录全路径
        if (!file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    //复制文件夹及其下面所有文件

    /**
     * 拷贝目录
     * @param srcName：源目录的抽象路径名
     * @param destName：目标目录的抽象路径名
     */
    public static void copyDirectory(String srcName, String destName) throws InterruptedException {
        //根据给定的源目录和目标目录的字符串创建File对象
        File source = new File(srcName);
        File dest = new File(destName);
        isexitsPath1(destName);

        //判断当前sourceFile是文件还是目录
        if(source.isFile()) {	//如果是文件
            //调用拷贝文件的方法
            copyFile(source, dest);
        }else{
            //先创建当前目录
            File newDest = new File(dest + "\\" + source.getName());
            newDest.mkdir();

            //确定该目录下的子文件和目录
            String[] files = source.list();

            //获取当前的源目录和目标目录的绝对路径名称
            String srcPath = source.getAbsolutePath();

            //转化路径名称为File对象
            File sFile;

            //对当前目录中的子目录和文件进行遍历
            for(int i = 0; i < files.length; i++) {
                //创建File对象
                sFile = new File(srcPath, files[i]);

                //判断当前的File对象是目录还是文件
                if(sFile.isFile()) {
                    //调用拷贝文件的方法
                    copyFile(sFile, newDest);
                }else{
                    //向目标路径创建目录
                    copyDirectory(sFile.getAbsolutePath(), newDest.getAbsolutePath());
                }
            }

        }
    }

    /**
     * 拷贝文件
     * @param source:源文件
     * @param dest：目标文件
     */
    public static void copyFile(File source, File dest) {
        //定义文件输入流对象
        FileInputStream fis = null;
        //定义文件输出流对象
        FileOutputStream fos = null;

        File file = new File(dest + "\\" + source.getName());
        try {
            //创建流对象
            fis = new FileInputStream(source);
            fos = new FileOutputStream(file);

            //定义缓冲数组
            byte[] buff = new byte[1024];
            //定义变量保存读到的字节数
            int len = 0;

            //循环读取
            while ((len = fis.read(buff)) != -1) {
                //将读到的数据写到输出流中
                fos.write(buff, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            copyDirectory("D:\\test\\java","D:\\test\\temp");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
