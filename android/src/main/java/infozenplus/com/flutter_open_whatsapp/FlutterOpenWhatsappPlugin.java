package infozenplus.com.flutter_open_whatsapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.app.Activity;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/** FlutterOpenWhatsappPlugin */
public class FlutterOpenWhatsappPlugin implements MethodCallHandler, FlutterPlugin, ActivityAware {

  Activity context;
  MethodChannel methodChannel;

  @Override
  public void onAttachedToEngine(@NonNull @org.jetbrains.annotations.NotNull FlutterPlugin.FlutterPluginBinding binding) {
    methodChannel = new MethodChannel(binding.getBinaryMessenger(), "flutter_open_whatsapp");
    methodChannel.setMethodCallHandler(this);
  }

  @Override
  public void onDetachedFromEngine(@NonNull @org.jetbrains.annotations.NotNull FlutterPlugin.FlutterPluginBinding binding) {  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equalsIgnoreCase("sendSingleMessage")) {
      Intent i = new Intent(Intent.ACTION_VIEW);
      try {
        String mobileNo = call.argument("mobileNo");
        String message = call.argument("message");
        assert mobileNo != null;
        assert message != null;
        String url = "https://wa.me/" + mobileNo.trim() + "?text=" + message.trim();
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        context.startActivity(i);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      result.notImplemented();
    }
  }


  @Override
  public void onAttachedToActivity(@NonNull @org.jetbrains.annotations.NotNull ActivityPluginBinding binding) {
    context = binding.getActivity();
  }

  @Override
  public void onDetachedFromActivityForConfigChanges() {}

  @Override
  public void onReattachedToActivityForConfigChanges(@NonNull @org.jetbrains.annotations.NotNull ActivityPluginBinding binding) { }

  @Override
  public void onDetachedFromActivity() {
    context = null;
  }
}
