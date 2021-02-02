import 'package:eshop/views/home/home_content/home_content.dart';
import 'package:eshop/views/home/home_content/home_content_mobile.dart';
import 'package:eshop/widgets/inner_sidebar/inner_sidebar.dart';
import 'package:flutter/material.dart';

class HomeViewMobile extends StatelessWidget {
  const HomeViewMobile({Key key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [InnerSideBar(), Container(child: HomeContent())],
    );
  }
}
