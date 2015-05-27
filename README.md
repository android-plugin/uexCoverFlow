# uexCoverFlow插件接口文档


### 平台支持

```
Android 2.3+
iOS 6.0+
```


### 添加一个CoverFlow

```
createNew（param）
var param={
	x:"0",//coverflow在屏幕上位置的x坐标
	y:"400",//coverflow在屏幕上位置的y坐标
	width:"-1",//coverflow宽度
	height:"800",//coverflow高度
	viewId:"111",//coverflow的Id,
	coverHeight:"360",//可选，默认480.中间图片的高度
	coverWidth:"240",//可选，默认360.中间图片的宽度
	urls:["res://1_1.jpg","res://1_2.jpg","res://1_3.jpg","res://1_4.jpg"]//图片的url
};

```

### 删除指定Id的CoverFlow

```
removeView()
var params = {
    viewId:"111"//要删除的coverflow的Id
};

```

### 图片点击回调

```
onItemPicClick(param)
var param={
    position://点击图片的位置
    viewId://对应CoverFlow的Id
};
```

### 图片滑动停止时的回调
```
onScrollToPosition(param)
var param={
    position://当前被展示图片的位置
    viewId://对应CoverFlow的Id
};
```

