package com.ghca.policeintf.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by DUDU on 2018/8/23.
 */
public class BSTTest {

    public static class Node {

        public int value;

        public Node leftNode;

        public Node rightNode;
    }

    public static void main(String[] args) throws IOException {

//        int[] arr = {23,4,3,8,19,26,88,8,9,15,77};
        int[] arr = {23,4,3,8,19,26,88,8,9,15};
//        int[] arr = {7,28,16,2,45,87,11,9};
//        int[] arr = {9,28,17,273,23,1,38,90};

        Node root = null;

//        root = CreateBiTree(root,arr,arr.length);
        root = createBstByWangFengWithoutRecursion(arr);

        System.out.println("����");
        preOrderView(root);

        System.out.println();
        System.out.println("����");

        middleOrderView(root);

        System.out.println();
        System.out.println("����");
        layerOrderView(root);

        paint(root);

    }

    /**
     * �������������,��,��,��
     */
    public static void preOrderView(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value);
        System.out.print(",");
        preOrderView(node.leftNode);
        preOrderView(node.rightNode);
    }

    /**
     * �������,�󣬸�����
     * @param node
     */
    public static void middleOrderView(Node node) {
        if (node == null) {
            return;
        }

        middleOrderView(node.leftNode);

        System.out.print(node.value);
        System.out.print(",");

        middleOrderView(node.rightNode);
    }


    //�ڶ����������в�����ҹؼ���key
    public static Node InsertBST(Node node,int key)
    {
        if (node == null)
        {
            node = new Node();
            node.leftNode = null;
            node.rightNode = null;
            node.value = key;
            return node;
        }

        if (key <= node.value) {
            node.leftNode = InsertBST(node.leftNode,key);
        } else {
            node.rightNode = InsertBST(node.rightNode,key);
        }

        return node;
    }


    //n������������d�У�treeΪ������������
    public static Node CreateBiTree(Node node, int d[], int n)
    {
        for (int i = 0; i < n; i++) {
            node = InsertBST(node, d[i]);
            System.out.println(node.value);
        }

        return node;
    }


    /**
     * �Ӹ��ڵ㿪ʼ����һ���ӽڵ�,�ݹ�
     * @param root
     * @param value
     * @return
     */
    public static Node insertNodeByWangFeng(Node root,int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            return root;
        }

        if (value <= root.value) {//����ȵ�ǰ�ڵ��ֵС�����������ǰ�ڵ����߲�,ֱ����ǰ�ڵ�Ϊ��
            root.leftNode = insertNodeByWangFeng(root.leftNode,value);
        } else {
            root.rightNode = insertNodeByWangFeng(root.rightNode,value);
        }
        return root;
    }


    /**
     * �ǵݹ����ڵ�,�ǵݹ�
     * @param root
     * @param value
     * @return ���������ɵĽڵ�
     */
    public static Node insertNodeByWangFengWithoutRecursion(Node root,int value) {
        if (root == null) {
            root = new Node();
            root.value = value;
            return root;
        }
        Node current = root;
        while (true) {
            if (value <= current.value) {//С�ڵ��ڵ�ǰ�ڵ�,�鿴��ǰ�ڵ��������
                if (current.leftNode == null) {//û����������
                    Node newNode = new Node();
                    newNode.value = value;
                    current.leftNode = newNode;
                    break;
                } else {//����������������������
                    current = current.leftNode;
                }
            } else {//������������
                if (current.rightNode == null) {//û��������
                    Node newNode = new Node();
                    newNode.value = value;
                    current.rightNode = newNode;
                    break;
                } else {//��������������
                    current = current.rightNode;
                }
            }
        }
        return root;
    }

    /**
     * ��һ��int��������һ��������������ǵݹ�
     * @param arr
     * @return
     */
    public static Node createBstByWangFengWithoutRecursion(int[] arr) {
        Node root = null;
        for (int i = 0 ; i < arr.length; i++) {
            root = insertNodeByWangFengWithoutRecursion(root,arr[i]);
        }
        return root;
    }


    public static void layerOrderView(Node root) {
        if (root == null) {
            System.out.println("����һ�ſ���");
            return;
        }
        List<Node> list = new ArrayList<Node>();
        list.add(root);
        Node current = root;
        int m = 0;
        while (true) {
            if (current.leftNode != null) {
                list.add(current.leftNode);
            }
            if (current.rightNode != null) {
                list.add(current.rightNode);
            }
            ++m;
            if (m == list.size()) {
                break;
            }
            current = list.get(m);
        }

        for (Node node : list) {
            System.out.print(node.value + ",");
        }
    }


    public static void layerOrderView2(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + ",");
        layerOrderView2(root.leftNode);
        layerOrderView2(root.rightNode);
    }




    public static void paint(Node root) throws IOException {
        System.out.println();
        if (root == null) {
            System.out.println("����һ�ſ���");
            return;
        }
        List<List<Node>> list = new ArrayList<List<Node>>();

//        list.add(root);
        List<Node> layerList = new ArrayList<Node>();
        layerList.add(root);
        list.add(layerList);

        List<Node> tmpList = null;

        for (int j = 0 ; j < list.size() ;j++) {
            layerList = list.get(j);
            tmpList = new ArrayList<Node>();

            /**
             * �ؼ��㣺ͬһ�����ݵ���һ��Ҳ��ͬһ��?????�ǲ�������?
             */
            for (int i = 0 ; i < layerList.size(); i++) {
                Node node = layerList.get(i);
                if (node.leftNode != null) {
                    tmpList.add(node.leftNode);
                }
                if (node.rightNode != null) {
                    tmpList.add(node.rightNode);
                }
            }

            if (tmpList.size() <= 0) {//����һ�㶼û�����ݣ�˵��������
                break;
            }

            layerList = tmpList;

            list.add(layerList);

        }


//        while (true) {
//            ++n;
//            if (current.leftNode != null) {
//                currentX = currentX - 60;
//                currentY = currentY + 60;
//                g.drawOval(currentX ,currentY,70,40);
//
//                list.add(current.leftNode);
//                System.out.println(current.leftNode.value);
//            }
//            if (current.rightNode != null) {
//                currentX = currentX + 60;
//                g.drawOval(currentX ,currentY,70,40);
//                list.add(current.rightNode);
//                System.out.println(current.rightNode.value);
//            }
//            ++m;
//            if (m == list.size()) {
//                break;
//            }
//            current = list.get(m);
//        }
//        System.out.println();

        int imgW = 1366;
        int imgH = 768;
        int nodeW = 50;
        int nodeH = 50;
        int fontW = 30;
        int layerH = 100;//���
        int nodeMargin = 80;//�ڵ���
        BufferedImage image = new BufferedImage(imgW,imgH,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g =  image.createGraphics();
        Font font = new Font("Times New Roman Italic", Font.BOLD, fontW);
        g.setFont(font);

//        int currentX = imgW /2 ;
        int currentY = 0;

        System.out.println("������:" + list.size());
        System.out.println("�������:");
        List<Integer> xIndexList = new ArrayList<Integer>();//��ÿһ���ÿ���ڵ��X����
        for (int i = 0; i < list.size(); i++) {
            List<Node> aaa = list.get(i);
            if (i == 0) {
                xIndexList.add(imgW / 2);
            }
            System.out.print("һ��:");
            for (int j = 0 ; j < aaa.size(); j++) {
                Node nnn = aaa.get(j);
//                int currentX = xIndexList.get(j);
                int currentX = xIndexList.remove(0);
//                g.drawOval(currentX,currentY,nodeW,nodeH);
                g.drawRect(currentX,currentY,nodeW,nodeH);
                g.drawString(nnn.value + "",currentX + nodeW / 2 - fontW /2  ,currentY + nodeH - fontW /2);

                System.out.print(nnn.value + ",");

                if (nnn.leftNode != null) {//������
                    g.drawLine(currentX + nodeW / 2,currentY + nodeH,currentX - nodeMargin,currentY + layerH);
                    xIndexList.add(currentX - nodeMargin);
                }
                if (nnn.rightNode != null) {//������
                    g.drawLine(currentX + nodeW / 2,currentY + nodeH,currentX + nodeMargin,currentY + layerH);
                    xIndexList.add(currentX + nodeMargin);
                }
            }
            System.out.println();
            //һ��ȡ��
            currentY = currentY + layerH;

        }
        g.dispose();
        ImageIO.write(image,"JPEG",new File("D:\\tmp\\���������.JPEG"));


    }
}
