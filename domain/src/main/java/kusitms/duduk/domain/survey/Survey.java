package kusitms.duduk.domain.survey;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import kusitms.duduk.domain.comment.Comment;
import kusitms.duduk.domain.global.Count;
import kusitms.duduk.domain.global.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Builder(toBuilder = true)
public class Survey {

    private Id id;
    private Id creatorId;
    private String description;
    private Count participants;
    @Default
    private List<Long> agreedUserIds = new ArrayList<>();
    @Default
    private List<Long> disagreedUserIds = new ArrayList<>();
    private List<Comment> comments;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void agree(Id userId) {
        if (agreedUserIds.contains(userId.getValue())) {
            agreedUserIds.remove(userId.getValue());
        }

        if (disagreedUserIds.contains(userId.getValue())) {
            disagreedUserIds.remove(userId.getValue());
        }

        this.agreedUserIds.add(userId.getValue());
    }

    public void disagree(Id userId) {
        if (disagreedUserIds.contains(userId.getValue())) {
            disagreedUserIds.remove(userId.getValue());
        }

        if (agreedUserIds.contains(userId.getValue())) {
            agreedUserIds.remove(userId.getValue());
        }

        this.disagreedUserIds.add(userId.getValue());
    }

    public boolean isVoted(Id userId) {
        return agreedUserIds.contains(userId.getValue()) || disagreedUserIds.contains(
            userId.getValue());
    }
}
