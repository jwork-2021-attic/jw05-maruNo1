package com.anish.calabashbros;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Maze {
	private class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	private int[][] map;
            
	//���
	private int startX;
	private int startY;
	//�յ�
	private int endX;
	private int endY;
	//��������ĵ�һ��
	public int stepX;
	public int stepY;
	//�������������ĸ������ߵķ���
	private int[] dx = {1,0,-1,0};
	private int[] dy = {0,1,0,-1};
	public Maze(int[][] map, int startX, int startY, int endX, int endY) {
		this.map = map;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}
	//������ȱ���Ѱ���Թ����е�����·���� x,y����ʼ��
	public void bfs() {
		Deque<Node> quene = new ArrayDeque<Node>();
		//�洢ÿ�����ǰ���ڵ㣬�����ӡ���·����·��
		int[][] pre = new  int[this.map.length][this.map[0].length];
		//�洢ÿ��������·��
		int[][] dis = new  int[this.map.length][this.map[0].length];
		for(int i=0; i<dis.length; i++) {
			for(int j=0; j<dis[0].length; j++) {
				dis[i][j] = 100;
			}
		}
		//�������ӣ����ľ�����Ϊ0�������Ϊ�ѷ���
		quene.add(new Node(this.startX, this.startY));
		dis[this.startX][this.startY] = 0;
		map[this.startX][this.startY] = 2;
		Node temp;
		//������ȱ������пɷ��ʵĵ㣬������ÿ��������·����ǰ���ڵ�
		while(!quene.isEmpty()) {
			temp = quene.poll();
			//����ÿ������ĸ�����
			for(int i=0; i<4; i++) {
				int tx = temp.x + dx[i];
				int ty = temp.y + dy[i];
				//����õ�û�з��ʹ������õ���Ӳ����Ϊ���ʹ�
				if(map[tx][ty] == 0) {
					//�Թ���ÿ��ֻ����һ�������Ծ����һ
					dis[tx][ty] = dis[temp.x][temp.y] + 1;
					pre[tx][ty] = i;
					map[tx][ty] = 2;
					quene.add(new Node(tx, ty));
				}
			}
		}//������dis�д�ŵľ������·��������ʱ����pre�����ӡ·��
		
		int a = this.endX;
		int b = this.endY;
		//System.out.printf("��(%d,%d)��(%d,%d)����̾����ǣ�%d��·��Ϊ��\n",
		//		this.startX, this.startY, a, b, dis[a][b]);
		//����������·����·�߲���ջ
		Stack<Node> stack = new Stack<Node>();
		stack.add(new Node(a, b));
		while(a != this.startX || b != this.startY) {
			int da = dx[pre[a][b]];
			int db = dy[pre[a][b]];
			a = a - da;
			b = b - db;
			stack.add(new Node(a,b));
		}
		//��ջ��˳����Ǵ���㵽�յ��·��
		Node p = stack.pop();
		p = stack.pop();
		stepX = p.x;
		stepY = p.y;
		//System.out.printf("(%d,%d)",stepX,stepY);
	}
}
