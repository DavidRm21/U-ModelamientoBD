


```sql
-- Creación de la tabla instrumentos musicales
CREATE TABLE TB_MUSCL_ISTMS(
    pk_muscl_istm number(10) primary key,
    photo_url varchar2(200),
    plc_crtr varchar2(30),
    crtr varchar2(30), 
    typ_istms varchar2(30),
    matrial varchar(50)
);

-- Creación de la tabla obras famosas
CREATE TABLE TB_FAM_WORKS(
    pk_fam_work number(10) primary key,
    year_complt number(4),
    author varchar2(50),
    sheet_msc varchar2(20),
    CONSTRAINT CHK_YEARCOMPLT CHECK (year_complt < 2023)
);

-- Creación de la tabla músicos
CREATE TABLE TB_MUSICIANS(
    pk_musician number(10) primary key,
    date_born date,
    date_death date,
    biogrhy varchar2(200)
);

-- Creación de la tabla generos en la musica
CREATE TABLE TB_GENRES (
    pk_genre number(10) primary key,
    var_charcts varchar2(200),
    origin varchar2(30),
    fk_musician number(10),
    fk_muscl_istm number(10),
    fk_fam_work number(10),
    CONSTRAINT FK_GENRES_MUSICIANS_FKMUSICIAN foreign key (fk_musician) references TB_MUSICIANS(pk_musician) on delete cascade,
    CONSTRAINT FK_GENRES_MUSCLISTMS_FKMUSCLISTMS foreign key (fk_muscl_istm) references TB_MUSCL_ISTMS(pk_muscl_istm) on delete cascade,
    CONSTRAINT FK_GENRES_FAMWORKS_FKFAMWORK foreign key (fk_fam_work) references TB_FAM_WORKS(pk_fam_work) on delete cascade
); 

-- Creación de la tabla epocas de la historia de la musica
CREATE TABLE TB_ERAS(
    pk_era number(10) primary key,
    name_era varchar2(12),
    rlvt_charcts varchar2(200),
    start_period varchar2(10),
    end_period varchar2(10),
    fk_genre number(10),
    CONSTRAINT NN_ERAS_NAMEERA NOT NULL,
    CONSTRAINT FK_ERAS_GENRES_FKGENRE foreign key (fk_genre) references TB_GENRES(pk_genre) on delete cascade
);
```

### Agregar comentarios a cada columna

```sql
-- Comentarios de las columnas TB_ERAS
COMMENT ON COLUMN TB_ERAS.pk_era IS 'Llave primaria de las epocas de la historia de la musica';
COMMENT ON COLUMN TB_ERAS.rlvt_charcts IS 'Texto para las caracteristicas relevantes de la epoca';
COMMENT ON COLUMN TB_ERAS.start_period IS 'Fecha de inicio de la epoca';
COMMENT ON COLUMN TB_ERAS.end_period IS 'Fecha de fin de la epoca';
COMMENT ON COLUMN TB_ERAS.fk_genre IS 'Relacion con la tabla generos';

-- Comentarios de las columnas TB_FAM_WORKS
COMMENT ON COLUMN TB_FAM_WORKS.pk_fam_work IS 'Llave primaria de las obras más famosas';
COMMENT ON COLUMN TB_FAM_WORKS.year_complt IS 'Annio en la que se completo la obra';
COMMENT ON COLUMN TB_FAM_WORKS.author IS 'Autores que participaron en la obra';
COMMENT ON COLUMN TB_FAM_WORKS.sheet_msc IS 'Partituras de la obra';

-- Comentarios de las columnas TB_MUSCL_ISTMS
COMMENT ON COLUMN TB_MUSCL_ISTMS.pk_muscl_istm IS 'Llave primaria de los instrumentos musicales';
COMMENT ON COLUMN TB_MUSCL_ISTMS.photo_url IS 'Imagen de referencia del intrumento musical';
COMMENT ON COLUMN TB_MUSCL_ISTMS.plc_crtr IS 'Lugar de los creadores';
COMMENT ON COLUMN TB_MUSCL_ISTMS.crtr IS 'Nombre del creador';
COMMENT ON COLUMN TB_MUSCL_ISTMS.typ_istms IS 'Tipo de instrumento  (percusión, viento, etc)';
COMMENT ON COLUMN TB_MUSCL_ISTMS.matrial IS 'Material del que está hecho el instrumento';

-- Comentarios de las columnas TB_MUSICIANS
COMMENT ON COLUMN TB_MUSICIANS.pk_musician IS 'Llave primaria del músico';
COMMENT ON COLUMN TB_MUSICIANS.date_born IS 'Fecha de nacimiento del músico';
COMMENT ON COLUMN TB_MUSICIANS.date_death IS 'Fecha en la que murio';
COMMENT ON COLUMN TB_MUSICIANS.biogrhy IS 'Una biografia corta de la vida del musico';

-- Comentarios de las columnas TB_GENRES
COMMENT ON COLUMN TB_GENRES.pk_genre IS 'Llave primaria de los generos musicales';
COMMENT ON COLUMN TB_GENRES.var_charcts IS 'Caracteristicas o descripcion del genero';
COMMENT ON COLUMN TB_GENRES.origin IS 'Origen del genero';
COMMENT ON COLUMN TB_GENRES.fk_musician IS 'Relación entre la tabla generos con la tabla de musicos';
COMMENT ON COLUMN TB_GENRES.fk_muscl_istm IS 'Relación entre la tabla de generos con la tabla de instrumentos musicales';
COMMENT ON COLUMN TB_GENRES.fk_fam_work IS 'Relación entre la tabla de generos con la tabla de obras más famosas';
```

```sql
INSERT ALL
    INTO TB_ERAS VALUES(1, 'Antiguedad', 'Predominio de la música vocal, Uso de escalas y modos musicales específicos, Importante papel en la religión y los rituales, Utilización de instrumentos antiguos como la lira, la flauta y la cítara, Música asociada con las civilizaciones egipcia, griega y romana.', 'Antiguedad', 'Siglo V')
    INTO TB_ERAS VALUES(2, 'Edad_Media', 'Dominio de la música sacra, especialmente el canto gregoriano, Desarrollo de la notación musical, Surgimiento de la música secular y los trovadores, Influencia de la Iglesia católica en la música, Uso de instrumentos como la vihuela y la flauta dulce.', 'Siglo V', 'Siglo XV')
    INTO TB_ERAS VALUES(3, 'Renacimiento', 'Polifonía prominente con voces independientes, Desarrollo de la música escrita para múltiples voces, Uso de la imprenta musical para la difusión, Compositores notables como Josquin des Prez y Palestrina, Música secular floreciente, incluyendo madrigales y chansons.', 'Siglo XV', 'Siglo XVI')
    INTO TB_ERAS VALUES(4, 'Barroco', 'Ornamentación y expresión musical intensa, Uso de la tonalidad y los modos mayores y menores, Desarrollo de nuevas formas musicales como la ópera y el concierto, Compositores destacados como Johann Sebastian Bach, George Frideric Handel y Antonio Vivaldi.', 'Siglo XVII', 'Siglo XVIII')
    INTO TB_ERAS VALUES(5, 'Clasicismo', 'Equilibrio y claridad en la música, Uso de formas clásicas como la sonata y el cuarteto de cuerda, Compositores notables como Wolfgang Amadeus Mozart y Ludwig van Beethoven, Énfasis en la estructura y la melodía.', 'Siglo XVIII', 'Siglo XIX')
    INTO TB_ERAS VALUES(6, 'Romanticismo', 'Expresión emocional intensa en la música, Uso de formas más libres y extensas, Compositores como Franz Schubert, Ludwig van Beethoven y Pyotr Ilyich Tchaikovsky, Tendencia hacia lo nacionalista y lo programático en la música.', 'Siglo XIX', 'Siglo XIX')
    INTO TB_ERAS VALUES(7, 'Siglo_XX', 'Diversidad de estilos y experimentación, Surgimiento de movimientos como el impresionismo, el dodecafonismo y la música minimalista, Utilización de tecnología electrónica en la música, Compositores contemporáneos como Igor Stravinsky, Arnold Schoenberg, John Cage y muchos otros.', 'Siglo XX', 'Actualidad')
```