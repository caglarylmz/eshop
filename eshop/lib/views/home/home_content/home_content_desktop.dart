import 'package:flutter/material.dart';

class HomeContentDesktop extends StatelessWidget {
  var items;
  HomeContentDesktop() {
    this.items = List<String>.generate(50, (i) => "Item $i");
  }
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) => ListTile(
                title: Text('${items[index]}'),
              )),
    );
  }
}
