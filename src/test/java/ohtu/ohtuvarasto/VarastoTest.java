package ohtu.ohtuvarasto;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriEiLuoNegatiivisenTilavuudenVarastoa() {
        Varasto varasto2 = new Varasto(-10);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriEiLuoNegatiivisenTilavuudenJaSaldonVarastoa() {
        Varasto varasto2 = new Varasto(-10, -5);
        assertEquals(0, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriAsettaaSopivanSaldon() {
        Varasto varasto2 = new Varasto(10, 5);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(5, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void toinenKonstruktoriHukkaaYlimaaraisenSaldon() {
        Varasto varasto2 = new Varasto(10, 15);
        assertEquals(10, varasto2.getTilavuus(), vertailuTarkkuus);
        assertEquals(10, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(10);
        varasto.lisaaVarastoon(-2);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisayksessaYlimaaraHukataan() {
        varasto.lisaaVarastoon(12);
       
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivisenMaaranOttaminenPalauttaaOikein() {
        varasto.lisaaVarastoon(8);
       
        assertEquals(0, varasto.otaVarastosta(-2), vertailuTarkkuus);
    }
    
    @Test
    public void liianSuurenMaaranOttaminenPalauttaaOikein() {
        varasto.lisaaVarastoon(8);
       
        assertEquals(8, varasto.otaVarastosta(9), vertailuTarkkuus);
    }
    
    @Test
    public void liianSuurenMaaranOttaminenTyhjentaaVaraston() {
        varasto.lisaaVarastoon(8);
        
        varasto.otaVarastosta(9);
       
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijonoesitysToimiiOikein() {
        varasto.lisaaVarastoon(8);

        assertEquals("saldo = 8.0, vielä tilaa 2.0", varasto.toString());
    }
}