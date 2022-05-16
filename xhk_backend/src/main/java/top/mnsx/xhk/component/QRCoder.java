package top.mnsx.xhk.component;

import com.google.zxing.WriterException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.mnsx.xhk.controller.BaseController;
import top.mnsx.xhk.utils.QRCodeUtil;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 二维码的展示功能Controller
 */
@CrossOrigin
@RestController
public class QRCoder extends BaseController {
    /**
     * 被发送请求后，响应一个二维码图片，关于删除对应优惠券，扣除金额的功能
     * @param resp
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping("/qrcode/{pid}")
    public void test(HttpServletResponse resp, @PathVariable("pid")Long pid) throws IOException, WriterException {
        BufferedImage code = QRCodeUtil.getQRCode("http://localhost:8081/packages/remove_package/" + pid);
        ImageIO.write(code, "JPEG", resp.getOutputStream());
    }
}
