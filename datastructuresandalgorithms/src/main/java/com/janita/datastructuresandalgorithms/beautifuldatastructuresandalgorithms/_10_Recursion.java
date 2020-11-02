package com.janita.datastructuresandalgorithms.beautifuldatastructuresandalgorithms;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * _10_Recursion
 *
 * @author zhucj
 * @since 20201126
 */
public class _10_Recursion {

    public static class ComputeRawNumberUseRecursion {

        /**
         * 周末你带着女朋友去电影院看电影，女朋友问你，咱们现在坐在第几排啊？电影院里面太黑了，看不清，没法数，现在你怎么办？
         *
         * f(n) = f(n-1) +1;f(1) = 1;
         *
         * @param n 其实就是当前排
         * @return 当前是第几排
         */
        public static int computeRawNumberUseRecursion(int n) {
            if (n == 1) {
                //终止条件f(1) = 1
                return 1;
            }
            //f(n) = f(n-1) +1
            return computeRawNumberUseRecursion(n - 1) + 1;
        }

        public static int computeRawNumberNotUseRecursion(int n) {
            int rawNumber = 1;
            for (int i = 2; i <= n; i++) {
                rawNumber = rawNumber + 1;
            }
            return rawNumber;
        }
    }

    /**
     * TODO 没明白
     */
    public static class ComputeWalkWaysUseRecursion {

        /**
         * 用于保存计算过的结果，避免重复计算
         */
        private Map<Integer, Integer> solvedMap = new HashMap<>();

        /**
         * 假如这里有 n 个台阶，每次你可以跨 1 个台阶或者 2 个台阶，请问走这 n 个台阶有多少种走法？如果有 7 个台阶，
         * 你可以 2，2，2，1 这样子上去，也可以 1，2，1，1，2 这样子上去，总之走法有很多，那如何用编程求得总共有多少种走法呢？
         *
         * 我们仔细想下，实际上，可以根据第一步的走法把所有走法分为两类，第一类是第一步走了 1 个台阶，另一类是第一步走了 2 个台阶。
         * 所以 n 个台阶的走法就等于先走 1 阶后，n-1 个台阶的走法 加上先走 2 阶后，n-2 个台阶的走法。用公式表示就是：
         * f(n) = f(n-1)+f(n-2)
         *
         * @param totalSteps 台阶的总数
         * @return totalSteps 个台阶的所有走法
         */
        public int computeWalkWaysUseRecursion(int totalSteps) {
            if (totalSteps == 1) {
                //终止条件f(1) = 1
                return 1;
            }
            if (totalSteps == 2) {
                //终止条件f(2) = 2
                return 2;
            }
            if (solvedMap.containsKey(totalSteps)) {
                return solvedMap.get(totalSteps);
            }

            //f(n) = f(n-1)+f(n-2)
            int ret = computeWalkWaysUseRecursion(totalSteps - 1) + computeWalkWaysUseRecursion(totalSteps - 2);
            solvedMap.put(totalSteps, ret);
            return ret;
        }

        public static int computeWalkWaysNotUseRecursion(int totalSteps) {
            if (totalSteps == 1) {
                //终止条件f(1) = 1
                return 1;
            }
            if (totalSteps == 2) {
                //终止条件f(2) = 2
                return 2;
            }
            int ways = 0;
            int pre = 2;
            int prePre = 1;
            for (int i = 3; i <= totalSteps; i++) {
                ways = pre + prePre;
                prePre = pre;
                pre = ways;
            }
            return ways;
        }
    }

    public static class FindRootReferrerId {

        @Data
        @AllArgsConstructor
        static class User {

            /**
             * 用户 id
             */
            private String actorId;

            /**
             * 推荐人 id
             */
            private String referrerId;
        }

        private static List<User> userList = Arrays.asList(
                new User("a", null),
                new User("b", "a"),
                new User("c", "b"),
                new User("d", "f"),
                new User("e", "a"),
                new User("f", "d"),
                new User("g", "f"),
                new User("h", "d"),
                new User("i", "a"),
                new User("j", "a"),
                new User("k", "a"));

        private static final Map<String, User> userMap = userList.stream().collect(Collectors.toMap(User::getActorId, Function.identity()));

        public static String findRootReferrerId(String actorId) {
            User user = userMap.get(actorId);
            if (user == null) {
                return null;
            }
            String referrerId = user.getReferrerId();
            if (referrerId == null) {
                return actorId;
            }
            return findRootReferrerId(referrerId);
        }
    }

}