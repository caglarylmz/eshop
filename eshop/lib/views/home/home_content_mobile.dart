import 'package:eshop/widgets/inner_sidebar/inner_sidebar.dart';
import 'package:flutter/material.dart';

class HomeContentMobile extends StatelessWidget {
  const HomeContentMobile({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisSize: MainAxisSize.max,
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        InnerSideBar(),
        Text("col"),
        Text("col"),
        Text("col"),
        Text("col"),
        Text("col"),
        Text("col"),
        Text("col"),
      ],
    );
  }
}
