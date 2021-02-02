import 'package:eshop/widgets/category_item/category_item.dart';
import 'package:flutter/material.dart';

class InnerSideBarMobile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      height: 80,
      child: Column(
        children: [
          Text(
            'CATEGORY',
            style: TextStyle(fontWeight: FontWeight.w800, height: 0.9),
          ),
          SizedBox(
            height: 10,
          ),
          Expanded(
            child: ListView(
              scrollDirection: Axis.horizontal,
              children: [
                CategoryItem("Büyükbaş"),
                CategoryItem("Küçükbaş"),
                CategoryItem("Kurbanlık"),
                CategoryItem("Kanatlı"),
                CategoryItem("Diğer Hayvanlar"),
                CategoryItem("Yem Maddeleri"),
              ],
            ),
          )
        ],
      ),
    );
  }
}
