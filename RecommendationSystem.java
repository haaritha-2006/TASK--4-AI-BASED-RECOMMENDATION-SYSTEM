import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.util.List;

public class RecommendationSystem {

    public static void main(String[] args) {
        try {
            // Load CSV data
            DataModel model = new FileDataModel(new File("data.csv"));

            // Compute item similarity
            ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Create item-based recommender
            GenericItemBasedRecommender recommender =
                    new GenericItemBasedRecommender(model, similarity);

            // Loop through all users
            long[] users = {1, 2, 3, 4, 5};
            for (long userId : users) {
                List<RecommendedItem> recommendations = recommender.recommend(userId, 3);

                System.out.println("Recommendations for User " + userId + ":");
                if (recommendations.isEmpty()) {
                    System.out.println("No recommendations available.");
                } else {
                    for (RecommendedItem item : recommendations) {
                        System.out.println("Item ID: " + item.getItemID() +
                                " | Score: " + String.format("%.2f", item.getValue()));
                    }
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
