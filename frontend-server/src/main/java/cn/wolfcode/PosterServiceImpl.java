package cn.wolfcode;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

@Service
public class PosterServiceImpl {
    //定义一个图片缓存类
    private BufferedImage image;
    private int imageWidth = 300;  //图片的宽度
    private int imageHeight = 500; //图片的高度

    /**
     *
     * @param productname 产品名称
     * @param originalPrice 原价
     * @param price 现价
     * @param urlone 图片路径
     * @param twourl 图片路径
     * @param threeurl 图片路径
     * @return
     * @throws IOException
     */
    public String graphicsGeneration(String productname, String originalPrice, String price,String urlone,String twourl,String threeurl) throws IOException {
        int H_title = 30;     //头部高度
        int H_image =50;
        int H_image_Text =50;
        int H_mainPic = 230;  //轮播广告高度
        int tit_image_text=(H_title+H_image+H_image_Text);
        int shops_2_top = H_title+H_image+H_image_Text+H_mainPic+20;
        //实例化缓存类
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        //设置图片的背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, imageWidth, imageHeight);

        //***********************页面头部
        Graphics title = image.createGraphics();
        //设置区域颜色
        title.setColor(new Color(255, 255, 255));
        //填充区域并确定区域大小位置
        title.fillRect(0, 0, imageWidth, H_title+20);
        //设置字体颜色，先设置颜色，再填充内容
        title.setColor(Color.black);
        //设置字体
        Font titleFont = new Font("黑体", Font.BOLD, 20);
        title.setFont(titleFont);
        title.drawString("驿货·精选", 100, H_title);
        //***********************页面头像
        BufferedImage bimg = null;
        BufferedImage bimg1 = null;
        BufferedImage bimg2 = null;
        Graphics mainPic1 = image.getGraphics();
        // 构造URL
        URL url = new URL("https://bj-yys.oss-cn-beijing.aliyuncs.com/bj-yys/微信图片_20200512173007.png");
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();
        try {
            bimg = javax.imageio.ImageIO.read(is);
        } catch (Exception e) {}
        if(bimg!=null){
            //第一个参数对应图片
            //第二个参数对应x轴
            //第三个参数对应y轴
            //第四个参数对应图片宽度
            //第五个参数对应图片高度
            //第六个参数图像观察者
            mainPic1.drawImage(bimg, 10, H_title+15, 60, 50, null);
            //填充区域并确定区域大小位置
            mainPic1.fillRect(75,H_title+15 , imageWidth, H_title+H_image);

            //设置字体颜色，先设置颜色，再填充内容
            mainPic1.setColor(Color.black);
            //设置字体样式和大小
            Font cname = new Font("宋体", Font.CENTER_BASELINE, 12);
            mainPic1.setFont(cname);
            String pname= "智能门锁人脸识别,智能门锁人脸识别，智能门锁人脸识别";
            //判断产品名称长度是否换行
            if (pname.length()>15){
                String pnamesub1 = pname.substring(0, 15);
                String pnamesub2 = pname.substring(15, pname.length());
                mainPic1.drawString(pnamesub1, 85, H_title+15+12);
                mainPic1.drawString(pnamesub2, 85, H_title+15+12+15);
            }else {
                mainPic1.drawString(pname, 85, H_title+15+12);
            }
            mainPic1.dispose();
        }

        //***********************插入原价格 原价格和现价格字体样式不同所以分开设置
        Graphics mainPicPrice = image.getGraphics();
        mainPicPrice.setColor(Color.red);
        //设置字体
        Font mainPicPriceFont = new Font("宋体", Font.BOLD, 24);
        mainPicPrice.setFont(mainPicPriceFont);
        mainPicPrice.drawString("￥168.00", 10, tit_image_text);
        mainPicPrice.dispose();
        //***********************插入现价格
        Graphics Price = image.getGraphics();
        Price.setColor(Color.gray);
        //设置字体
        Font PriceFont = new Font("宋体", Font.BOLD, 14);
        Price.setFont(PriceFont);
        Price.drawLine(140,tit_image_text-7,250,tit_image_text-7);

        Price.drawString("吊牌价￥299.00", 140, tit_image_text);
        Price.dispose();
        //***********************插入产品大图，从oss下载路径直接获取
        Graphics mainPic = image.getGraphics();
        // 构造URL https://aliyuncs.com/1593412615%281%29.jpg
        URL middleurl = new URL("图片路径");
        URL middleurl2 = new URL("图片路径");
        URL middleurl3 = new URL("图片路径");

        // 打开连接
        URLConnection middlecon = middleurl.openConnection();
        URLConnection middlecon2 = middleurl2.openConnection();
        URLConnection middlecon3 = middleurl3.openConnection();
        //设置请求超时为5s
        middlecon.setConnectTimeout(5*1000);
        middlecon2.setConnectTimeout(5*1000);
        middlecon3.setConnectTimeout(5*1000);
        // 输入流
        InputStream middleis = middlecon.getInputStream();
        InputStream middleis2 = middlecon2.getInputStream();
        InputStream middleis3 = middlecon3.getInputStream();
        try {
            bimg = javax.imageio.ImageIO.read(middleis);
            bimg1 = javax.imageio.ImageIO.read(middleis2);
            bimg2 = javax.imageio.ImageIO.read(middleis3);
            if(bimg!=null){
                mainPic.drawImage(bimg, 10, tit_image_text+10, imageWidth-20, H_mainPic, null);
            }
            if(bimg1!=null){
                mainPic.drawImage(bimg, 10, shops_2_top, 85, 85, null);
            }
            if(bimg2!=null){
                mainPic.drawImage(bimg2, 105, shops_2_top, 85, 85, null);
            }
            mainPic.dispose();
        } catch (Exception e) {}

        //*************小程序码
        Graphics particularsImage3 = image.getGraphics();
        try {
            bimg = javax.imageio.ImageIO.read(new File("这个可以调用生成二维码的接口把二维码加入海报"));
            if(bimg!=null){
                particularsImage3.drawImage(bimg, 210, shops_2_top, 70, 70, null);
                particularsImage3.dispose();
            }
        } catch (Exception e) {}

        Graphics PriceText = image.getGraphics();
        PriceText.setColor(Color.gray);
        //设置字体
        Font PriceTextFont = new Font("ITALIC", Font.PLAIN, 11);
        PriceText.setFont(PriceTextFont);

        PriceText.drawString("长按识别小程序", 205, shops_2_top+85);
        PriceText.drawString("码购买该商品", 208, shops_2_top+100);
        PriceText.dispose();

        //转换上传到oss
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(image, "jpg", imOut);
        InputStream inputStream = new ByteArrayInputStream(bs.toByteArray());

        /*// 把缓存图片保存到本地文件  本地调试时使用不需要就可以注释上
        boolean jpg = ImageIO.write(image, "jpg", new File("c:\\caerteImage.jpg"));*/
        String imgUrl="";
        return imgUrl;
    }
}