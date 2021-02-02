import 'package:eshop/widgets/category_item/category_item.dart';
import 'package:flutter/material.dart';

class InnerSideBarDesktop extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      width: 260,
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 10,
          ),
          Column(
            children: [
              CategoryItem("Büyükbaş"),
              CategoryItem("Küçükbaş"),
              CategoryItem("Kurbanlık"),
              CategoryItem("Kanatlı"),
              CategoryItem("Diğer Hayvanlar"),
              CategoryItem("Yem Maddeleri"),
            ],
          )
        ],
      ),
    );
  }
}
