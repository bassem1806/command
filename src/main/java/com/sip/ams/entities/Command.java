package com.sip.ams.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Command {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private Date datecommand;

    @Column(name ="commandequantité")

    private  int quantite;




    /**** geter and setter methode ****/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatecommand() {
        return datecommand;
    }

    public void setDatecommand(Date datecommand) {
        this.datecommand = datecommand;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Command( Date datecommand, int quantite) {
        this.id = id;
        this.datecommand = datecommand;
        this.quantite = quantite;

    }

    public Command( ) {

    }

    /**** Many To One provider****/

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "provider_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Provider provider;

    /**** Getter and setter provider****/
    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**** Many To One article ****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "article_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Article article;

    /**** geter an setter article ****/
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    /**** Many To One  direction general****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "directiong_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Directiong directiong;

    /**** geter an setter direction g ****/
    public Directiong getDirectiong() {
        return directiong;
    }

    public void setDirectiong(Directiong directiong) {
        this.directiong = directiong;
    }

    /**** Many To One direction****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "direction_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Direction direction;

    /**** geter and setter direction****/

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**** Many To One Sous direction****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sousdirection_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Sousdirection sousdirection;

    /**** geter and setter sous direction****/
    public Sousdirection getSousdirection() {
        return sousdirection;
    }

    public void setSousdirection(Sousdirection sousdirection) {
        this.sousdirection = sousdirection;
    }

    /**** Many To One Stock****/
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "stock_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Stock stock;


    /**** geter and setter stock ****/

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
