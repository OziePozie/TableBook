package org.beauty.tablebook.models.media.reels;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class ReelsDTOResponse{
    Long id;
    Date createdAt;
    String content;

    static ReelsDTOResponse fromEntity(Reels reels){

        return ReelsDTOResponse.builder()
                .id(reels.getId())
                .createdAt(reels.getCreatedAt())
                .content(reels.getContent())
                .build();


    }
}
