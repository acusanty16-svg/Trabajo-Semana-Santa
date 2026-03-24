package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Libro  implements Serializable {
    private static Long serialVersionUID = 123L;
    private long id;
    private long Year;
    private String Title;
    private String Handle;
    private String Publisher;
    private String ISBN;
    private long Pages;
    private Object[] Notes;
    private Object[] Villains;

}
