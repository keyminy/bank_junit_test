package shop.mtcoding.bank.domain.account;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.mtcoding.bank.domain.user.User;

@NoArgsConstructor //Full constructor가 생기면 꼭 넣어주자
//스프링이 User객체를 생성할 때, 빈 생성자로 new를 하기때문.
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name="account_tb")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false,length = 20)
    private Long number; //계좌번호
    @Column(nullable = false,length = 4)
    private Long password; //계좌비번
    @Column(nullable = false)
    private Long balance; //잔액(기본값 : 1000원)

    //항상 ORM에서 FK의 주인은 Many Entity쪽이다.(FK는 account가 가짐)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user; //설정을 안해주면 기본값으로, 'user_id'형식으로 만들어짐

    @CreatedDate //Insert시 자동으로 들어감
    @Column(nullable=false)
    private LocalDateTime createdAt;
    
    @LastModifiedDate //Insert,Update시 자동으로 들어감
    @Column(nullable=false)
    private LocalDateTime updatedAt;

    @Builder
    public Account(Long id, Long number, Long password, Long balance, User user, LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.number = number;
        this.password = password;
        this.balance = balance;
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
}
