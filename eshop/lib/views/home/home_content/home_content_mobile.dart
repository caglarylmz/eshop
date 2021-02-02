import 'package:flutter/material.dart';

class HomeContentMobile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Expanded(
      child: GridView.count(
        crossAxisCount: 2,
        children: List.generate(
            50,
            (index) => Center(
                  child: Text("Advert $index"),
                )),
      ),
    );
  }
}
