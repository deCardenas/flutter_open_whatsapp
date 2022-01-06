import 'dart:async';

import 'package:flutter/services.dart';

class FlutterOpenWhatsapp {
  static const MethodChannel _channel =
      const MethodChannel('flutter_open_whatsapp');

  static Future<Null> sendSingleMessage(String mobileNo, String msg) async {
    await _channel.invokeMethod(
        'sendSingleMessage', {'mobileNo': mobileNo, 'message': msg});
  }
}
