import 'package:flutter/material.dart';

class InnerSideBarDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 400,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 20,
          ),
          Column(
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
