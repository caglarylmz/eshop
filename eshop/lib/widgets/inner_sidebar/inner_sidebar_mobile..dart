import 'package:flutter/material.dart';

class InnerSideBarMobile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 10,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text("abc"),
              Text("abc"),
              Text("abc"),
              Text("abc"),
              Text("abc"),
              Text("abc"),
            ],
          )
        ],
      ),
    );
  }
}
