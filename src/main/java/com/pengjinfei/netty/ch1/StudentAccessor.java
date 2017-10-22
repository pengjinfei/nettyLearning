package com.pengjinfei.netty.ch1;

import lombok.*;
import lombok.experimental.Accessors;

/**
 * Created on 10/21/17
 *
 * @author Pengjinfei
 */
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Accessors(fluent = true)
@Data
public class StudentAccessor {

    @NonNull
    private String id;

    private String name;
}
