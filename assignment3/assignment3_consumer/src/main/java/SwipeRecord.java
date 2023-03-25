import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SwipeRecord {

  private Integer swiper;
  private Integer swipee;
  private boolean isLike;
  private static Map<Integer, int[]> likeOrDislikeMap = new ConcurrentHashMap<>();
  private static Map<Integer, Set<Integer>> listSwipeRight = new ConcurrentHashMap<>();


  public SwipeRecord() {
  }

  public static Map<Integer, int[]> getLikeOrDislikeMap() {
    return likeOrDislikeMap;
  }

  public static void addToLikeOrDislikeMap(Integer swiper, boolean isLike) {
    int[] likeOrDislike = likeOrDislikeMap.get(swiper);
    if (likeOrDislike == null) {
      likeOrDislike = new int[]{0, 0};
    }
    if (isLike) {
      likeOrDislike[0]++;
    } else {
      likeOrDislike[1]++;
    }
    likeOrDislikeMap.put(swiper, likeOrDislike);
  }


  public static void addToLikeMap(Integer swiper, Integer swipee, boolean isLike) {
    if (isLike) {
      Set<Integer> swipeRightSet = listSwipeRight.computeIfAbsent(swiper, k -> new HashSet<>());
      if (swipeRightSet.size() < 100) {
        swipeRightSet.add(swipee);
      }
    }
  }

//  public static String toNewString() {
//    StringBuilder sb = new StringBuilder();
//    for (Map.Entry<Integer, int[]> entry : likeOrDislikeMap.entrySet()) {
//      Integer swiper = entry.getKey();
//      int[] likeOrDislike = entry.getValue();
//      sb.append("Swiper ID: ").append(swiper).append(", Likes: ").append(likeOrDislike[0])
//          .append(", Dislikes: ").append(likeOrDislike[1]).append("\n");
//    }
//    return sb.toString();
//  }
}




