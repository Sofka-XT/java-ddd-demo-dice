package org.example.nomemientan;

import co.com.sofka.application.ApplicationEventDrive;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.infraestructure.asyn.SubscriberEvent;
import co.com.sofka.infraestructure.bus.EventBus;
import co.com.sofka.infraestructure.handle.CommandWrapper;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.operation.OperationRequest;
import org.springframework.restdocs.operation.OperationResponse;
import org.springframework.restdocs.operation.OperationResponseFactory;
import org.springframework.restdocs.operation.preprocess.OperationPreprocessor;
import org.springframework.restdocs.payload.RequestFieldsSnippet;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public abstract class CommandBaseIntegrationTest {

    @Autowired
    protected SubscriberEvent subscriberEvent;
    @Autowired
    private ApplicationEventDrive applicationEventDrive;
    @SpyBean
    private EventBus bus;
    @Captor
    private ArgumentCaptor<DomainEvent> eventArgumentCaptor;

    private RequestSpecification documentationSpec;

    @BeforeAll
    static void cleanAll() { }

    @BeforeEach
    public void setUp(RestDocumentationContextProvider restDocumentation) {
        this.documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation))
                .build();

    }

    @LocalServerPort
    private void initRestAssured(final int localPort) {
        RestAssured.port = localPort;
        RestAssured.baseURI = "http://localhost";

    }

    protected void fireEvent(DomainEvent event, String aggregateName, String aggregateId) {
        event.setAggregateName(aggregateName);
        event.setAggregateRootId(aggregateId);
        applicationEventDrive.fire(event);
    }

    protected void executor(CommandWrapper request, RequestFieldsSnippet requestFieldsSnippet, int numEvents) {
        var commandType = request.getCommandType();
        RestDocumentationFilter docs = getSpecDoc(numEvents, commandType,
                requestFieldsSnippet
        );
        given(documentationSpec)
                .filter(docs)
                .contentType(ContentType.JSON)
                .body(new Gson().toJson(request))
                .when()
                .post("/api/command")
                .then()
                .assertThat().statusCode(is(200));

    }

    protected RestDocumentationFilter getSpecDoc(int numEvents, String name, RequestFieldsSnippet requestFieldsSnippet) {
        return document(name,
                preprocessRequest(prettyPrint()),
                preprocessResponse(new OperationPreprocessor() {
                    @Override
                    public OperationRequest preprocess(OperationRequest operationRequest) {
                        return operationRequest;
                    }

                    @Override
                    public OperationResponse preprocess(OperationResponse operationResponse) {
                        wait6s();
                        verify(bus, times(numEvents)).publish(eventArgumentCaptor.capture());
                        return new OperationResponseFactory().create(
                                200,
                                operationResponse.getHeaders(),
                                new Gson().toJson(eventArgumentCaptor.getAllValues()).getBytes()
                        );
                    }
                }, prettyPrint()),
                requestFieldsSnippet
        );
    }

    private void wait6s() {
        try {
            Thread.sleep(16000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}