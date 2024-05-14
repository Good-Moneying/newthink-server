package kusitms.duduk.application.comment.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kusitms.duduk.application.global.entity.BaseEntity;
import kusitms.duduk.application.newsletter.persistence.entity.NewsLetterJpaEntity;
import kusitms.duduk.application.user.persistence.entity.UserJpaEntity;
import kusitms.duduk.domain.comment.vo.Perspective;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "comments")
@Builder(toBuilder = true)
public class CommentJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "news_letter_id", nullable = false)
    private NewsLetterJpaEntity newsLetter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "summarized_content")
    private String summarizedContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "perspective")
    private Perspective perspective;

    @Column(name = "like_count")
    private int likeCount;

}
