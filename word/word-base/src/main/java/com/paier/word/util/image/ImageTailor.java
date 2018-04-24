package com.paier.word.util.image;

import javax.validation.constraints.NotNull;

public class ImageTailor {

	@NotNull(message = "图片路径不能为空")
    private String imgPath;
	@NotNull(message = "图片不可为空")
    private Integer x;
    private Integer y;
    private Integer width;
    private Integer height;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "ImageTailorVo{" +
                "imgPath='" + imgPath + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
