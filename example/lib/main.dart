import 'package:flutter/material.dart';

import 'package:flutter_open_whatsapp/flutter_open_whatsapp.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
            child: MaterialButton(
          onPressed: () {
            FlutterOpenWhatsapp.sendSingleMessage("918179015345", "Hello");
          },
          child: Text('Running on: $_platformVersion\n'),
        )),
      ),
    );
  }
}
