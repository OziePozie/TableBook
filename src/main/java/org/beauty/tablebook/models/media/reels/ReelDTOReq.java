package org.beauty.tablebook.models.media.reels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beauty.tablebook.models.restaurants.Restaurants;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReelDTOReq{

    String content;


    public Reels toEntity(){
        Reels reels = new Reels();
        reels.setCreatedAt(Date.from(Instant.now()));
        reels.setContent(this.content);


        return reels;
    }

}
