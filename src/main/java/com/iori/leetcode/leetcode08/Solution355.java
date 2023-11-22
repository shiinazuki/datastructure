package com.iori.leetcode.leetcode08;

import java.util.*;

/**
 * 355. 设计推特
 */
public class Solution355 {

    //推特类
    private class Tweet {
        private int id; //推文id
        private int timestamp; //发推文的时间戳
        private Tweet next;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }

    //用户id 和推文的对应关系
    private Map<Integer, Tweet> twitter;
    //用户id 和他关注的用户列表的对应关系
    private Map<Integer, Set<Integer>> followings;
    //全局使用的时间戳字段 用户每发布一条推文之前 +1;
    private static int timestamp = 0;
    //合并k 组推文使用的数据结构
    private static PriorityQueue<Tweet> maxHeap;

    public Solution355() {
        followings = new HashMap<>();
        twitter = new HashMap<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> -o1.timestamp + o2.timestamp);
    }

    //发布文章
    public void postTweet(int userId, int tweetId) {
        timestamp++;
        if (twitter.containsKey(userId)) {
            Tweet oldHead = twitter.get(userId);
            Tweet newHead = new Tweet(tweetId, timestamp);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        } else {
            twitter.put(userId, new Tweet(userId, timestamp));
        }
    }

    //获取最新10篇文章(包括自己和关注者)
    public List<Integer> getNewsFeed(int userId) {
        //由于是全局使用的  使用之前需要清空
        maxHeap.clear();
        //如果自己发了推文 也要算上
        if (twitter.containsKey(userId)) {
            maxHeap.offer(twitter.get(userId));
        }

        Set<Integer> followingList = followings.get(userId);
        if (followingList != null && followingList.size() > 0) {
            for (Integer followingId : followingList) {
                Tweet tweet = twitter.get(followingId);
                if (tweet != null) {
                    maxHeap.offer(tweet);
                }
            }
        }

        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10) {
            Tweet head = maxHeap.poll();
            res.add(head.id);
            if (head.next != null) {
                maxHeap.offer(head.next);
            }
            count++;
        }
        return res;

    }

    //新增关注
    public void follow(int followerId, int followeeId) {
        //被关注人不能是自己
        if (followerId == followeeId) {
            return;
        }
        //获取我自己的关注列表
        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) {
            Set<Integer> init = new HashSet<>();
            init.add(followeeId);
            followings.put(followerId,init);
        }else {
            if (followingList.contains(followerId)) {
                return;
            }
            followingList.add(followeeId);
        }
    }

    //取消关注
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) {
            return;
        }
        Set<Integer> followingList = followings.get(followerId);
        if (followingList == null) {
            return;
        }
        followingList.remove(followeeId);
    }

    public static void main(String[] args) {

        Solution355 twitter = new Solution355();
        twitter.postTweet(1, 1);
        List<Integer> res1 = twitter.getNewsFeed(1);
        System.out.println(res1);

        twitter.follow(2, 1);

        List<Integer> res2 = twitter.getNewsFeed(2);
        System.out.println(res2);

        twitter.unfollow(2, 1);

        List<Integer> res3 = twitter.getNewsFeed(2);
        System.out.println(res3);
    }


}
