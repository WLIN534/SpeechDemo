# SpeechDemo
####讯飞语音文字转语音
###预备工作
 导入SDK Android studio中.so放到jniLibs 
 
 在工程AndroidManifest.xml文件中添加如下权限：
 ```
 <!--连接网络权限，用于执行云端语音能力 -->  
<uses-permission android:name="android.permission.INTERNET"/>  
<!--获取手机录音机使用权限，听写、识别、语义理解需要用到此权限 -->  
<uses-permission android:name="android.permission.RECORD_AUDIO"/>  
<!--读取网络信息状态 -->  
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>  
<!--获取当前wifi状态 -->  
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>  
<!--允许程序改变网络连接状态 -->  
<uses-permission android:name="android.permission.CHANGE_NETWORK_STATE"/>  
<!--读取手机信息权限 -->  
<uses-permission android:name="android.permission.READ_PHONE_STATE"/>  
<!--读取联系人权限，上传联系人需要用到此权限 -->  
<uses-permission android:name="android.permission.READ_CONTACTS"/>  
```
 
*1、 初始化
初始化即创建语音配置对象，只有初始化后才可以使用MSC的各项服务。建议将初始化放在程序入口处（如Application、Activity的onCreate方法),初始化代码如下：
// 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn  
SpeechUtility.createUtility(context, SpeechConstant.APPID +"=12345678");  ** 

###语音合成
与语音听写相反，合成是将文字信息转化为可听的声音信息，让机器像人一样开口说话。合成的调用方法如下：

```
//1.创建SpeechSynthesizer对象, 第二个参数：本地合成时传InitListener  
SpeechSynthesizer mTts= SpeechSynthesizer.createSynthesizer(context, null);  
//2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类  
mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");//设置发音人  
mTts.setParameter(SpeechConstant.SPEED, "50");//设置语速  
mTts.setParameter(SpeechConstant.VOLUME, "80");//设置音量，范围0~100  
mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端  
//设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”  
//保存在SD卡需要在AndroidManifest.xml添加写SD卡权限  
//如果不需要保存合成音频，注释该行代码  
mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.pcm");  
//3.开始合成  
mTts.startSpeaking("科大讯飞，让世界聆听我们的声音", mSynListener);    
//合成监听器  
private SynthesizerListener mSynListener = new SynthesizerListener(){  
    //会话结束回调接口，没有错误时，error为null  
    public void onCompleted(SpeechError error) {}  
    //缓冲进度回调  
    //percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。  
    public void onBufferProgress(int percent, int beginPos, int endPos, String info) {}  
    //开始播放  
    public void onSpeakBegin() {}  
    //暂停播放  
    public void onSpeakPaused() {}  
    //播放进度回调  
    //percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.  
    public void onSpeakProgress(int percent, int beginPos, int endPos) {}  
    //恢复播放回调接口  
    public void onSpeakResumed() {}  
//会话事件回调接口  
    public void onEvent(int arg0, int arg1, int arg2, Bundle arg3) {}  
```

