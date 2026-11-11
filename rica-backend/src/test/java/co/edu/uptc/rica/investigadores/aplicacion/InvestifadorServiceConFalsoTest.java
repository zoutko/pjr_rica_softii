package co.edu.uptc.rica.investigadores.aplicacion;

import co.edu.uptc.rica.ricaapi.investigadores.dominio.Investigador;
import org.junit.jupiter.api.Test;
import co.edu.uptc.rica.ricaapi.investigadores.aplicacion.InvestigadorService;
import co.edu.uptc.rica.ricaapi.investigadores.aplicacion.InvestigadorFactory;

import static org.assertj.core.api.Assertions.assertThat;

class InvestigadorServiceConFalsoTest {

  @Test
  void registraYRecuperaUnInvestigadorSinNingunaDependenciaDeSpringNiDeBaseDeDatos() {
    RepositorioInvestigadoresFalso repositorio = new RepositorioInvestigadoresFalso();
    InvestigadorFactory factory = new InvestigadorFactory(repositorio, null);
    InvestigadorService service = new InvestigadorService(factory, repositorio);

    Investigador guardado = service.registrar("Ana Torres", "ana.torres@uptc.edu.co", "GIT-UPTC");

    assertThat(guardado.getId()).isNotNull();
    assertThat(service.buscarPorId(guardado.getId()).getNombreCompleto()).isEqualTo("Ana Torres");
  }
}